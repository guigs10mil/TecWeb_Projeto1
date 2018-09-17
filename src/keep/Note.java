package keep;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Note {
	private Integer id;
	private String color;
	private Long dateCreated;
	private String text;
	private Integer idUser;
	private String label;
	private String rightdate;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Calendar date) {
		this.dateCreated = date.getTimeInMillis();
	}
	
	public String getrightdate() {
		Calendar data = Calendar.getInstance();
		data.setTimeInMillis(this.dateCreated);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.rightdate = sdf.format(data.getTime());
		return rightdate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}