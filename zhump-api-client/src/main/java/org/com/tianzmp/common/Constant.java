package org.com.tianzmp.common;



public class Constant{
	
	/***********redis缓存*********************/
	public enum redisEnumKey{
		/**商品分类**/
		CATEGORY("category_list","分类数据"),
		/**单个商品key*/
		GOODS("goods_","单个商品key"),
		/**所有商品key*/
		GOODS_LIST("goods_list","所有商品"),
		/**用户购物车**/
		USER_CART("user_cart_","用户rediskey");
		
		
		private redisEnumKey(String key, String msg) {
			this.key = key;
			this.msg = msg;
		}

		private String key;
		
		private String msg;

		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		
	}
	
}
