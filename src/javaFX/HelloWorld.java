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

		Text scenetitle = new Text("�����빤�Ų�ѯ");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
		grid.add(scenetitle, 0, 0, 2, 1);

		// ����Label���󣬷ŵ���0�У���1��
		Label userName = new Label("����:");
		grid.add(userName, 0, 1);

		// �����ı�����򣬷ŵ���1�У���1��
		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

//		       Label pw = new Label("Password:");
//		       grid.add(pw, 0, 2);

//		       PasswordField pwBox = new PasswordField();
//		       grid.add(pwBox, 1, 2);

		// ������¼��ť
		Button btn = new Button("��ѯ");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);// ����ť�ؼ���Ϊ�ӽڵ�
		grid.add(hbBtn, 1, 4);// ��HBox pane�ŵ�grid�еĵ�1�У���4��

//		Button loginButton = new Button("��¼");
//		HBox hbBtn1 = new HBox(10);
//		hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
//		hbBtn1.getChildren().add(loginButton);// ����ť�ؼ���Ϊ�ӽڵ�
//		grid.add(hbBtn1, 2, 3);// ��HBox pane�ŵ�grid�еĵ�1�У���4��

		final Text actiontarget = new Text();// ����������ʾ��Ϣ���ı�
		grid.add(actiontarget, 1, 6);

		// ��btn��ť���¼�
		btn.setOnAction(new EventHandler<ActionEvent>() {// ע���¼�handler
			@Override
			public void handle(ActionEvent e) {
				
				try {
					Boolean isSuccess = JsoupCookieCraw.findByWorkId(userTextField.getText());
					if (isSuccess) {
						actiontarget.setFill(Color.DARKGREEN);
						actiontarget.setText("��ϲ��֤���ɹ����ɣ�����D�̵�javaWordTest�ļ����²���");
					} else {
						actiontarget.setFill(Color.FIREBRICK);// ��������ɫ��� firebrick red
						actiontarget.setText("��ѯ��������Ƿ��������");
					}

				} catch (IOException e1) {
					actiontarget.setFill(Color.FIREBRICK);// ��������ɫ��� firebrick red

					actiontarget.setText("δ��¼�����¼");
					login();
				}
			}
		});
		
		
		//����¼��ť���¼�
//		loginButton.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				login();
//				actiontarget.setFill(Color.DARKGREEN);
//				actiontarget.setText("��ϲ����¼�ɹ�,���ѯ��");
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
			System.err.println("��¼����");
		}
	}

	
	public static void main(String[] args) {
		System.err.println("ready");
		launch(args);
	}
}