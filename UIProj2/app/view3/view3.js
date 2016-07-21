'use strict';

angular.module('myApp.view3', ['ngRoute', 'ngCookies'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view3/', {
            templateUrl: 'view3/view3.html',
            controller: 'View3Ctrl'
        });
    }])
    .controller('View3Ctrl', function ($scope, $http, $routeParams, $cookies) {

        $scope.total_items = $cookies.get('cart_count');
        var retrievedCookie = $cookies.get('orderid');
        console.log(retrievedCookie);

        $scope.validateQuantity = function () {
            console.log('validating quantity');
            var prod_dict = {};
            var flag = true;
            $http.get('http://127.0.0.1:8000/api/products')
                .success(function (data, status, headers, config) {
                    console.log(data)

                    data.data.forEach(function (ob) {
                        prod_dict[ob.id] = ob.availablequantity;
                    });
                    $http.get('http://127.0.0.1:8000/api/orders/' + retrievedCookie + '/orderlineitem/')
                        .success(function (data, status, headers, config) {
                            console.log(data)
                            $scope.OrderLineItems = data;
                            var total = 0;
                            data.data.forEach(function (arrayItem) {
                                if (prod_dict[arrayItem.product_id] < arrayItem.quantityordered) {

                                    alert('Please check quantity of ' + arrayItem.productname)
                                    flag = false;
                                }
                                total = total + (arrayItem.price * arrayItem.quantityordered)
                            });
                            console.log('TOTAL:');
                            console.log(total);
                            $scope.totalPurchase = total + ' INR';
                            $cookies.put('totalCost', total);

                            if (flag === false) {

                            }
                            // alert('Please check quantity of '+arrayItem.productname)
                            else
                                window.location.href = "#!/view4/";

                        })
                        .error(function (data, status, header, config) {
                        });

                })
                .error(function (data, status, header, config) {
                });


            return false;


        }

        $scope.quantityChange = function (orderLineItemId, newQuantity, d) {
            console.log(d);
            console.log('quantity change function')
            console.log(newQuantity)
            var orderLineItemPatchUrl = 'http://127.0.0.1:8000/api/orders/' + $cookies.get('orderid') + '/orderlineitem/' + orderLineItemId + '/';
            var data = {};
            data['quantityordered'] = d.quantityordered;
            console.log(data['quantityordered'])
            $http.patch(orderLineItemPatchUrl, data)
                .then(
                    function (response) {
                        console.log('OrderLineItem Patch completed');

                        console.log(response);

                    },
                    function (response) {
                        console.log('failure in   OrderLinePatch');
                        console.log(response)

                    }
                );
        }
        $http.get('http://127.0.0.1:8000/api/orders/' + retrievedCookie + '/orderlineitem/')
            .success(function (data, status, headers, config) {
                console.log(data)
                $scope.OrderLineItems = data;
                var total = 0;
                data.data.forEach(function (arrayItem) {
                    total = total + (arrayItem.price * arrayItem.quantityordered)
                });
                console.log('TOTAL:');
                console.log(total);
                $scope.totalPurchase = total + ' INR';
                $cookies.put('totalCost', total);

            })
            .error(function (data, status, header, config) {
            });

    });
