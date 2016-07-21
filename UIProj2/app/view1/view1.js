'use strict';

angular.module('myApp.view1', ['ngRoute', 'ngCookies'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl'
        });
    }])

    .controller('View1Ctrl', function ($scope, $http, $cookies) {

        console.log('in view1 controller');
        console.log($cookies.get('cart_count'));
        $scope.total_items = $cookies.get('cart_count');

        $scope.updateProduct = function (categoryDescription) {

            $scope.categoryDesc = categoryDescription;
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