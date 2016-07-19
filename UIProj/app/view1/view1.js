'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', function($scope,$http) {

  $scope.updateProduct = function (categoryDescription) {
    $scope.categoryDesc=categoryDescription;
  };

  $http.get('http://127.0.0.1:8000/api/products')
      .success(function (data, status, headers, config) {
        console.log(data)
        $scope.Details = data;
      })
      .error(function (data, status, header, config) {
      });

  $http.get('http://127.0.0.1:8000/api/categories')
      .success(function (data, status, headers, config) {
        console.log(data)
        $scope.CategoriesDetails = data;


      })
      .error(function (data, status, header, config) {
      });

});