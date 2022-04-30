/**
 * 
 */
 const app = angular.module("shopping-cart-app",[]);
 
 app.controller("shopping-cart-ctrl", function($scope, $http){

	paypal.Buttons({
		style: {
			layout: 'vertical',
			color: 'blue',
			shape: 'rect',
			label: 'paypal'
		},

		createOrder: function(data, actions) {
			
			// Set up the transaction
			return actions.order.create({
				purchase_units: [{
					amount: {
						value: $scope.cart.amount
					}
				}]
			});
		},
		
		onApprove: function(data, actions) {
			$scope.order.purchase();
			alert('Bạn đã thanh toán ví điện tử Paypal thành công !!!');
		},
		


	}).render('#paypal-button-container');
 	
 	$scope.cart = {
 		items: [],
 		
 		add(id){
 			var item = this.items.find(item => item.id == id);
 			if(item){
 				item.qty++;
 				this.saveToLocalStorage();
 			}else{
 				$http.get(`/rest/products/${id}`).then(resp => {
 					resp.data.qty = 1;
 					
 					this.items.push(resp.data);
 					this.saveToLocalStorage();
 				})
 			}
 		},
 		
 		remove(id){
 			var index = this.items.findIndex(item => item.id == id);
 			this.items.splice(index, 1);
 			this.saveToLocalStorage();
 		},
 		
 		clear(){
 			this.items = [];
 			this.saveToLocalStorage();
 		},
 		
 		amt_of(item){
 		
 		},
 		
 		get count(){
 			return this.items
 				.map(item => item.qty)
 				.reduce((total,qty) => total += qty,0);
 		},
 		
 		get amount(){
 			return this.items
 				.map(item => item.qty * item.price)
 				.reduce((total,qty) => total += qty,0);
 		},
 		
 		saveToLocalStorage(){
 			var json = JSON.stringify(angular.copy(this.items));
 			localStorage.setItem("cart", json);
 		},
 		
 		loadFromLocalStorage(){
 			var json = localStorage.getItem("cart");
 			this.items = json ? JSON.parse(json) : [];
 		}
 		
 	}
 
 	$scope.cart.loadFromLocalStorage();
 	
 	$scope.order = {
 		createDate: new Date(),
 		
 		address: "",
 		
 		account:{
 			username:$("#username").text()
 		},
 		
 		get orderDetails(){
 			return $scope.cart.items.map(item => {
 				return {
 					product: {
 						id: item.id
 					},
 					price: item.price,
 					quantity: item.qty
 				}
 			})
 		},
 		
 		purchase(){
 			var order = angular.copy(this);
 			
 			$http.post("/rest/orders", order).then(resp => {
 				alert("Đặt hàng thành công!");
 				$scope.cart.clear();
 				location.href = "/order/detail/" + resp.data.id;
 			})
 			.catch(error => {
 				alert("Đặt hàng lỗi!");
 				console.log(error);
 			});
 		}
 	}
 	
 	// create account
 	
 	$scope.account = {
 		item: {},
 		
 		add(id){
 			$http.post("/rest/accounts", id).then(resp => {
 				$scope.account.item = resp.data;
 				alert("Tạo tài khoản thành công!");
 			})
 			.catch(error => {
 				alert("Tạo tài khoản thất bại!");
 				console.log(error);
 			});
 		},

 		
 	}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 
 })