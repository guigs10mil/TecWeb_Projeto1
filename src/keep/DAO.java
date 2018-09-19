package keep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {

	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/keep?useTimezone=true&serverTimezone=UTC",
					"root", "Magonegro1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verifyLogin(User user) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT password FROM User WHERE name=?");

			stmt.setString(1, user.getName());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			if (user.getPassword().equals(rs.getString("password"))) {
				rs.close();
				stmt.close();
				return true;
			} else {
				rs.close();
				stmt.close();
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean verifySignUp(User user) {
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(name) FROM User WHERE name=?");
			stmt.setString(1, user.getName());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					rs.close();
					stmt.close();
					return true;
				} else {
					System.out.println(rs.getInt(1));
					rs.close();
					stmt.close();
					return false;
				}
			} else {
				System.out.println("Error: could not get the record counts");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int getIdUser(User user) {
		String sql = "SELECT id_user FROM User WHERE name = ?";
		PreparedStatement stmt;
		int id = 0;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				rs.close();
				stmt.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public String getUsername(int idUser) {
		String sql = "SELECT name FROM User WHERE id_user = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String username = rs.getString(1);
				rs.close();
				stmt.close();
				return username;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Could'nt get Username";
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id_user"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addUser(User user) {
		String sql = "INSERT INTO user" + "(name,password) values(?,?)";

		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addNote(Note note) {
		String sql = "INSERT INTO note" + "(color, date_created, text, id_user, label) values(?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, note.getColor());
			stmt.setLong(2, note.getDateCreated());
			stmt.setString(3, note.getText());
			stmt.setInt(4, note.getIdUser());
			stmt.setString(5, note.getLabel());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void removeUser(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM note WHERE id_user=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt = connection.prepareStatement("DELETE FROM user WHERE id_user=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void removeNote(Integer id) {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM note WHERE id_note=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		String sql = "UPDATE user SET " + "name=?, password=? WHERE id_user=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateNote(Note note) {
		String sql = "UPDATE note SET " + "date_created=?, text=?, label=? WHERE id_note=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, note.getDateCreated());
			stmt.setString(2, note.getText());
			stmt.setString(3, note.getLabel());
			stmt.setInt(4, note.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> getColors(int id) {
		List<String> colors = new ArrayList<String>();
		try {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT DISTINCT color FROM Note WHERE id_user=? ORDER BY color");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				colors.add(rs.getString("color"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colors;
	}

	public List<Note> getNotes(int id, List<String> colors) {
		List<Note> notes = new ArrayList<Note>();
		try {
			String prepared = "SELECT * FROM Note WHERE ( id_user=?";
			if (colors.size() > 0) {
				prepared += " AND (";
				for (String color : colors) {
					if (colors.indexOf(color) == (colors.size() - 1)) {
						prepared += "color=" + "'" + color + "'" + ")";
					} else {
						prepared += "color=" + "'" + color + "'" + " OR ";
					}
				}
			}
			prepared += " ) ORDER BY date_created DESC";
			PreparedStatement stmt = connection.prepareStatement(prepared);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.setId(rs.getInt("id_note"));
				note.setColor(rs.getString("color"));
				Calendar data = Calendar.getInstance();
				data.setTimeInMillis(rs.getLong("date_created"));
				note.setDateCreated(data);
				note.setText(rs.getString("text"));
				note.setIdUser(rs.getInt("id_user"));
				note.setLabel(rs.getString("label"));
				notes.add(note);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notes;
	}
}