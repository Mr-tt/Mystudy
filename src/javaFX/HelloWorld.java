package javaFX;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import yifuyuan.JsoupCookieCraw;

public class HelloWorld extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Welcome");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("请输入工号查询");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		grid.add(scenetitle, 0, 0, 2, 1);

		// 创建Label对象，放到第0列，第1行
		Label userName = new Label("工号:");
		grid.add(userName, 0, 1);

		// 创建文本输入框，放到第1列，第1行
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

//		       Label pw = new Label("Password:");
//		       grid.add(pw, 0, 2);

//		       PasswordField pwBox = new PasswordField();
//		       grid.add(pwBox, 1, 2);

		// 创建登录按钮
		Button btn = new Button("查询");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);// 将按钮控件作为子节点
		grid.add(hbBtn, 1, 4);// 将HBox pane放到grid中的第1列，第4行

//		Button loginButton = new Button("登录");
//		HBox hbBtn1 = new HBox(10);
//		hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn1.getChildren().add(loginButton);// 将按钮控件作为子节点
//		grid.add(hbBtn1, 2, 3);// 将HBox pane放到grid中的第1列，第4行

		final Text actiontarget = new Text();// 增加用于显示信息的文本
		grid.add(actiontarget, 1, 6);

		// 给btn按钮绑定事件
		btn.setOnAction(new EventHandler<ActionEvent>() {// 注册事件handler
			@Override
			public void handle(ActionEvent e) {
				
				try {
					Boolean isSuccess = JsoupCookieCraw.findByWorkId(userTextField.getText());
					if (isSuccess) {
						actiontarget.setFill(Color.DARKGREEN);
						actiontarget.setText("恭喜！证明成功生成，请在D盘的javaWordTest文件夹下查找");
					} else {
						actiontarget.setFill(Color.FIREBRICK);// 将文字颜色变成 firebrick red
						actiontarget.setText("查询出错，检查是否输入错误");
					}

				} catch (IOException e1) {
					actiontarget.setFill(Color.FIREBRICK);// 将文字颜色变成 firebrick red

					actiontarget.setText("未登录，请登录");
					login();
				}
			}
		});
		
		
		//给登录按钮绑定事件
//		loginButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				login();
//				actiontarget.setFill(Color.DARKGREEN);
//				actiontarget.setText("恭喜！登录成功,请查询！");
//			}
//		});
		
		Scene scene = new Scene(grid, 500, 275);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	
	
	protected void login()  {
		try {
			JsoupCookieCraw.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("登录出错");
		}
	}

	
	public static void main(String[] args) {
		System.err.println("ready");
		launch(args);
	}
}