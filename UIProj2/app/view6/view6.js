'use strict';

angular.module('myApp.view6', ['ngRoute','ngCookies'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view6/', {
            templateUrl: 'view6/view6.html',
            controller: 'View6Ctrl'
        });
    }])
    .controller('View6Ctrl', function($scope,$http,$routeParams,$cookies) {

        console.log('in view6 ctrl');
        var retrievedCookie=$cookies.get('orderid');
        console.log(retrievedCookie);

        $http.get('http://127.0.0.1:8000/api/orders/'+retrievedCookie+'/orderlineitem/')
            .success(function (data, status, headers, config) {
                console.log(data)
                $scope.OrderLineItems = data;
                $cookies.remove("orderid");
                $cookies.remove("cart_count");
            })
            .error(function (data, status, header, config) {
            });





    });
