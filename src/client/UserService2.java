package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class UserService2 {
	
	private Connection conn;
	
	public UserService2() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost/CESI?user=guest&password=guest");
	}
	
	public List getUserDisponibles() throws Exception {
		PreparedStatement ps = conn.prepareStatement("select nom from users where selectionne=0");
		ResultSet rs= ps.executeQuery();
		List result=new ArrayList<>();
		while (rs.next()) {
			result.add(rs.getString(1));
		}
		return result;
	}

	public List getUserSelectionnes() throws Exception {
		PreparedStatement ps = conn.prepareStatement("select nom from users where selectionne<>0");
		ResultSet rs= ps.executeQuery();
		List result=new ArrayList<>();
		while (rs.next()) {
			result.add(rs.getString(1));
		}
		return result;
	}

	public void addUser(String nom) throws Exception {
		PreparedStatement ps=conn.prepareStatement(
				String.format("insert into USERS (nom, selectionne) values (\"%s\", 0)", nom));
		ps.executeUpdate();
	}

	public void toggleUser(String nom) throws Exception {
		PreparedStatement ps=conn.prepareStatement(
				String.format("update USERS set selectione=(selectionne+1)%2 where nom=\"%s\"", nom));
		ps.executeUpdate();
	}

	public void removeUser(String nom) throws Exception {
		PreparedStatement ps=conn.prepareStatement(
				String.format("delete from USERS where nom=?", nom));
		ps.setString(1, nom);
		ps.executeUpdate();
	}

}
