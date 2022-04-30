app.controller("gallery-ctrl", function($scope, $http){
	$scope.filenames = [];
	
	$scope.list = function(){
		$http.get("/rest/upload/images").then(resp => {
			$scope.filenames = resp.data;
			console.log("Sussces: ", resp.data);
		}).catch(error => {
			console.log("Errors: ", error);
		});
	}
	
	$scope.list();
	
	$scope.delete = function(filename){
		$http.delete(`/rest/upload/images/${filename}`).then(resp => {
			let i = $scope.filenames.findIndex(name => name == filename);
			$scope.filenames.splice(i, 1);
			alert("Xóa ảnh thành công !");
		}).catch(error => {
			console.log("Errors: ", error);
		});
	}
	
	
	
	
	
	
});