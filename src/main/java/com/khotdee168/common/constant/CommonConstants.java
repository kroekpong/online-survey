package com.khotdee168.common.constant;

import java.math.RoundingMode;

public class CommonConstants {

	public static final String REPORT_TEMPLATE = "D:/App/config/TPS/print_template.jasper";
	public static final String REPORT_TEMPLATE_TPS = "D:/App/config/TPS/print_template_tps.jasper";
	public static final String PRINT_ENABLE = "1";
	
	 public static final class STATUS {
		 public static final String ACTIVE = "1";
		 public static final String DISABLED = "0";
	 }

	public static final class ENCODING {
		public static final String UTF8 = "utf-8";
	}

	public static final class BACKEND_USER {
		public static final String SYSTEM = "SYSTEM";
	}

	public static final class EditDataTableAction {
		public static final String CREATE = "create";
		public static final String EDIT = "edit";
		public static final String REMOVE = "remove";
	}

	public static class WEBSERVICE {
		public static final String STATUS_SUCCESS = "0";
		public static final String STATUS_FAILED = "1";

		public static final String STATUS_SUCCESS_TEXT = "SUCCESS";
		public static final String STATUS_FAILED_TEXT = "FAILED";
	}

	public static class HTTP {
		public static final String STATUS_SUCCESS = "200";
		public static final String STATUS_FAILED = "-1";
		public static final String STATUS_DUPLICATE = "400";

		public static final String STATUS_SUCCESS_TEXT = "SUCCESS";
		public static final String STATUS_FAILED_TEXT = "FAILED";
	}

	public static class ROLES {
		public static final String ROLE_ADMIN = "ROLE_ADMIN";
		public static final String ROLE_ENGINEER = "ROLE_ENGINEER";
		public static final String ROLE_SUPPLIER = "ROLE_SUPPLIER";

	}
	
	public static class CHANNEL {
		public static final String SMS = "SMS";
		public static final String CALL_CENTER = "CC";
		public static final String QR = "QR";
		
	}
	
	public static class CUSTOMER_STATUS {
		public static final String PENDING = "0";
		public static final String CLOSE = "1";
		
	}

	public static class NUMBER_SCALE {
		public static final int SCALE_SHOW = 5;
		public static final int SCALE_CAL = 4;
		public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	}

}
