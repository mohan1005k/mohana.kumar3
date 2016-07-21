'use strict';

angular.module('myApp.view2', ['ngRoute','ngCookies'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2/:param', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($scope,$http,$routeParams,$cookies) {

    $scope.total_items=$cookies.get('cart_count');
    document.getElementById("addToCartSuccess").style.display = "none";
    document.getElementById("addToCartFailure").style.display = "none";

    console.log('view2 controller');
  console.log($routeParams.param);
  $http.get('http://127.0.0.1:8000/api/products/'+ $routeParams.param)
      .success(function (data, status, headers, config) {
        console.log(data)
        $scope.ProductDetails = data;
      })
      .error(function (data, status, header, config) {
      });

  $scope.addOrderLineItem=function(productId,productPrice,orderId,quantityOrdered)
  {

      var orderLineItemCreateUrl='http://127.0.0.1:8000/api/orders/'+orderId+'/orderlineitem/';
      var data={}
      data['product_id']=productId;
      data['price']=productPrice;
      data['quantityordered']=$scope.quantity;
      $http.post(orderLineItemCreateUrl, data)
          .then(
              function(response){
                  console.log('OrderLineItem Create success');
                  console.log(response.data);

                  var retrievedCartCookie=$cookies.get('cart_count');
                  console.log(retrievedCartCookie);
                  if(typeof(retrievedCartCookie) === 'undefined')
                  {
                      $cookies.put('cart_count',1);


                  }
                  else
                  {
                      console.log(retrievedCartCookie);
                      console.log(parseInt(retrievedCartCookie));
                      var inc=1;
                      var new_count=parseInt(retrievedCartCookie)+inc;
                      console.log(new_count);
                      $cookies.put('cart_count',new_count);
                  }
                  document.getElementById("addToCartSuccess").style.display = "block";

                  document.getElementById("addToCartSuccess").style.visibility = "visible";
                  $scope.total_items=$cookies.get('cart_count');
                  // success callback
              },
              function(response){
                  console.log('failure');

                  document.getElementById("addToCartFailure").style.visibility = "visible";
                  // failure callback
              }
          );

  }
  $scope.addToCart = function(productId,productPrice)
  {
      var retrievedCookie=$cookies.get('orderid');
      console.log('retrieved cookie in addToCart:')
      console.log(retrievedCookie);
      console.log(typeof(retrievedCookie))
      if(typeof(retrievedCookie) === 'undefined')
      {
          var orderCreateUrl='http://127.0.0.1:8000/api/orders/'
          var data={}
          $http.post(orderCreateUrl, data)
              .then(
                  function(response){
                      console.log('Order create success');
                      console.log(response);
                      console.log(response.data.data.id);
                      $cookies.put('orderid',response.data.data.id);
                      retrievedCookie=$cookies.get('orderid');
                      $scope.addOrderLineItem(productId,productPrice,retrievedCookie,$scope.quantity)

                  },
                  function(response){
                      console.log('failure in   order create');
                      console.log(response)


                  }
              );

      }
      else
      {
          $scope.addOrderLineItem(productId,productPrice,retrievedCookie,$scope.quantity)

      }

  }
  //$scope.param = $routeParams.param;
  //console.log($scope.param);
});