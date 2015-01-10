(function() {
    'use strict';
    app.controller('HomeCtrl', ['$scope', 'FetchFileFactory',
        function($scope, FetchFileFactory) {
            $scope.fileViewer = 'Please select a file to view its contents';
            $scope.nodeSelected = function(e, data) {
                var _l = data.node.li_attr;
                if (_l.isLeaf) {
                    var original = data.node.original,
                        test = original.test;

                    $scope.fileViewer = original.text;
                    $scope.icon = original.icon? original.icon + '-big' : '';

                    requestFile(_l.base, 'jsSource');
                    requestFile(original.source, 'waccSource');

                    $scope.testInput = test.input;
                    $scope.expOut = test.expOut;
                    $scope.testOut = test.log;
                    // setTimeout(Rainbow.color, 50);
                } else {
                    //http://jimhoskins.com/2012/12/17/angularjs-and-apply.html//
                    $scope.$apply(function() {
                        $scope.fileViewer = 'Please select a file to view its contents';
                    });
                }
            };

            function requestFile(filename, target) {
                FetchFileFactory.fetchFile(filename).then(function(data) {
                    var _d = data.data;
                    if (typeof _d == 'object') {
                        //http://stackoverflow.com/a/7220510/1015046//
                        _d = JSON.stringify(_d, undefined, 2);
                    }
                    $scope[target] = _d;
                });
            }
        }
    ]);
}());