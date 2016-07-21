'use strict';

angular.module('myApp.view5', ['ngRoute', 'ngCookies'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view5/', {
            templateUrl: 'view5/view5.html',
            controller: 'View5Ctrl'
        });
    }])
    .controller('View5Ctrl', function ($scope, $http, $routeParams, $cookies) {
        $scope.total_items = $cookies.get('cart_count');
        console.log('in view5 ctrl');
        var retrievedCookie = $cookies.get('orderid');
        console.log(retrievedCookie);

        $scope.submitFeedback = function () {

            console.log('Trying to submit feedback');
            if (typeof($scope.comments) === 'undefined') {
                alert('Kindly fill required fields')

            }
            else {


                console.log('inside submit feedback');
                console.log($scope.username);
                console.log($scope.comments);

                var feedbackURL = 'http://127.0.0.1:8000/api/feedback/';
                var data = {};
                data['username'] = $scope.username;
                data['description'] = $scope.comments;
                $http.post(feedbackURL, data)
                    .then(
                        function (response) {
                            console.log('Feedback posted');

                            console.log(response);

                        },
                        function (response) {
                            console.log('failure in feedback submission');
                            console.log(response)

                        }
                    );
                alert('Your feedback has been taken');
            }

        }

    });
