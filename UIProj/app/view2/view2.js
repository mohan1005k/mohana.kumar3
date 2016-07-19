'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2/:param', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($scope,$http,$routeParams) {

  console.log('view2 constroller');
  console.log($routeParams.param);
  $http.get('http://127.0.0.1:8000/api/products/'+ $routeParams.param)
      .success(function (data, status, headers, config) {
        console.log(data)
        $scope.ProductDetails = data;
      })
      .error(function (data, status, header, config) {
      });

  //$scope.param = $routeParams.param;
  //console.log($scope.param);
});