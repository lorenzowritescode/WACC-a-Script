(function() {
    'use strict';
    app.factory('FetchFileFactory', ['$http',
        function($http) {
            var _factory = {};
            _factory.fetchFile = function(file) {
                return $http.get('/api/resource?resource=' + encodeURIComponent(file));
            };
            return _factory;
        }
    ]);

    app.factory('TestFileFactory', ['$http',
        function($http) {
            var _factory = {};
            _factory.testFile = function(testEnvironment) {
                return $http.get('/api/test?resource=' + encodeURIComponent(JSON.stringify(testEnvironment)));
            };
            return _factory;
        }
    ]);
}());