'use strict';

angular.module('myApp.view4', ['ngRoute','ngCookies'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view4/', {
            templateUrl: 'view4/view4.html',
            controller: 'View4Ctrl'
        });
    }])
    .controller('View4Ctrl', function($scope,$http,$routeParams,$cookies) {
        $scope.total_items=$cookies.get('cart_count');

        document.getElementById("onlinepayment").style.visibility = "hidden";

        console.log('in view4 ctrl');
        var retrievedCookie=$cookies.get('orderid');
        console.log(retrievedCookie);

        $scope.radiochange=function()
        {
            document.getElementById("onlinepayment").style.visibility = "visible";
            console.log('inside radio change');
        }
        $scope.radiochange2=function()
        {
            console.log('inside radio change 2');
            document.getElementById("onlinepayment").style.visibility = "hidden";

        }
        $scope.submitOrder=function() {

            if (typeof($scope.username) === 'undefined' || typeof($scope.address) === 'undefined') {
                alert('Kindly fill required fields')

            }
            else {


                console.log('inside submit order');
                console.log($scope.username);
                console.log($scope.address);

                var orderCompleteUrl = 'http://127.0.0.1:8000/api/orders/' + $cookies.get('orderid') + '/'
                var data = {};
                data['username'] = $scope.username;
                data['address'] = $scope.address;
                data['status'] = 'COMPLETED';
                $http.patch(orderCompleteUrl, data)
                    .then(
                        function (response) {
                            console.log('Order completion success');
                       //     $cookies.remove("orderid");
                       //     $cookies.remove("cart_count");
                            console.log(response);

                        },
                        function (response) {
                            console.log('failure in   order completion');
                            console.log(response)

                        }
                    );
                window.location.href="#!/view6/";
            }

        }

    });

