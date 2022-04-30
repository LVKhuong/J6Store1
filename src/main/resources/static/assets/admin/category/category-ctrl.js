app.controller("category-ctrl", function($scope, $http){
	$scope.items = [];
	$scope.form = {};
	
	$scope.initialize = function(){
		// load categories
		$http.get("/rest/categories")
			.then(resp => {
				$scope.items = resp.data;
			})
			.catch(error => {
			alert("Loi them moi san pham");
			console.log("Error",error);
		});
	}
	
	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			id: "",
			name: ""
		};
	}
	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
	}
	
	$scope.create = function(){
		var form = angular.copy($scope.form);
		$http.post("/rest/categories", form).then(resp => {
			$scope.items.push(resp.data);
			alert("Thêm mới thành công!");
			$scope.reset();
			})
		.catch(error => {
			alert("Loi them moi san pham");
			console.log("Error",error);
		});
	}
	
	$scope.update = function(){
		var form = angular.copy($scope.form);
		$http.put(`/rest/categories/${form.id}`, form)
			.then(resp => {
				var index = $scope.items.findIndex(p => p.id == resp.data.id);
				$scope.items[index] = resp.data;
				alert("Cập nhập chủng loại thành công!");
			})
			.catch(error => {
				alert("Loi cap nhap san pham");
				console.log("Error",error);
			});
	}
	
	$scope.delete = function(item){
		$http.delete(`/rest/categories/${item.id}`)
			.then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				
				$scope.reset();
				alert("Xoá chủng loại thành công!");
			})
			.catch(error => {
				alert("Loi cap nhap san pham");
				console.log("Error",error);
			});
	}
	
	$scope.pager = {
		page: 0,
		size: 5,
		
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size)
		},
		
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		
		first(){
			this.page = 0;
		},
		
		prev(){
			this.page--;
			if(this.page < 0){
				this.first();
			}
		},
		
		next(){
			this.page++;
			if(this.page >= this.count){
				this.last();
			}
		},
		
		last(){
			this.page = this.count - 1;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
});