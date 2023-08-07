module configRicktrader {
	exports application;
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens controller to javafx.fxml;
}
