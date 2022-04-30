
app.controller("product-ctrl",function($scope,$http){
	$scope.items = [];
	$scope.form = {};
	$scope.cates = [];
	
	
	$scope.initialize = function(){
		// load products
		$http.get("/rest/products")
			.then(resp => {
				$scope.items = resp.data;
				$scope.items.forEach(item => {
					item.createDate = new Date(item.createDate)
				})
			});
			
		// load categories
		$http.get("/rest/categories")
			.then(resp => {
				$scope.cates = resp.data;
				
			});
			
	}
	
	
	
	$scope.initialize();
	
	$scope.reset = function(){
		$scope.form = {
			createDate: new Date(),
			image: "abcd.jpg",
			avaiable: true
		}
	}
	
	$scope.edit = function(item){
		$scope.form = angular.copy(item);
	}
	
	$scope.create = function(){
		var item = angular.copy($scope.form);
		$http.post("/rest/products",item)
			.then(resp => {
				resp.data.createDate = new Date(resp.data.createDate)
				$scope.items.push(resp.data);
				$scope.reset();
				alert("Them moi san pham thanh cong");
			}).catch(error => {
				alert("Loi them moi san pham");
				console.log("Error",error);
			});
	}
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`, item)
			.then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				alert("Cap nhap san pham thanh cong");
			}).catch(error => {
				alert("Loi cap nhap san pham");
				console.log("Error",error);
			});
	}
	
	$scope.delete = function(item){
		$http.delete(`/rest/products/${item.id}`)
			.then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items.splice(index, 1);
				$scope.reset();
				alert("Xoa san pham thanh cong");
			}).catch(error => {
				alert("Loi xoa san pham");
				console.log("Error",error);
			});
	}
	

	$scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		
		$http.post("/rest/upload/images",data,{
			transformRequest: angular.identity,
			headers: {"Content-Type": undefined}
		}).then(resp => {
			alert("Upload image thanh cong");
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Loi upload hinh anh");
			console.log("Error", error);
		});
	}
	
	$scope.pager = {
		page: 0,
		size: 5,
		
		get items(){
		var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
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