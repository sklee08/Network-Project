package Control;

import java.net.Socket;

import View.EnterRoomErrorView;
import View.confirmView;
import View.createRoomView;
import View.dupLoginErrorView;
import View.enterRoomView;
import View.lobbyView;
import View.loginErrorView;
import View.loginView;
import View.roomView;
import View.signUpErrorView;
import View.signUpView;

public class GUIController {
	
	
	private lobbyView lobby;
	private loginView login;
	private roomView room;
	private signUpView sign;
	private confirmView confirm;
	private loginErrorView loginError;
	private signUpErrorView signError;
	private createRoomView createroomview;
	private enterRoomView enterroomview;
	private roomView roomview;
	private dupLoginErrorView dup;
	private EnterRoomErrorView erev;
	
	
//	private String nickname;

	
	public GUIController() {

	}
	
	public void popRoomError() {
		this.erev = new EnterRoomErrorView();
		erev.setVisible(true);
	}
	
	public void popDup() {
		this.dup = new dupLoginErrorView();
		dup.setVisible(true);
	}
	
	
	public void popRoomView(String id, int roomNum) {
		this.roomview = new roomView(id, roomNum);
		this.roomview.setVisible(true);
	}
	
	
	public void popEnterRoomView(String id) {
		this.enterroomview = new enterRoomView(id);
		enterroomview.setVisible(true);
	}
	

	public void popSignUpError() {
		try {
			this.signError = new signUpErrorView();
			signError.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void popLoginError() {
		try {
			this.loginError = new loginErrorView();
			loginError.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void popLobby(String id) {
		try {
			this.lobby = new lobbyView(id);
			lobby.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void popLogin() {
		try {
			this.login = new loginView();
			login.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void popSign() {
		try {
			this.sign = new signUpView();
			sign.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void popConfirm() {
		try {
			this.confirm = new confirmView();
			confirm.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void popCreateRoomView(String id, Socket socket) {
		this.createroomview = new createRoomView(id, socket);
		createroomview.setVisible(true);
	}
}
