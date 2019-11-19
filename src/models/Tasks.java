//DTO用クラスTasks.java
package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity                            //エンティティクラスであることを指定
@Table(name="tasks")        //エンティティにマッピングされる物理テーブル名を指定
public class Tasks {
	@Id                           //主キーフィールドを宣言
	@Column(name="id")    //エンティティの各フィールドにマッピングされる物理テーブルのカラム名を指定
	@GeneratedValue(strategy=GenerationType.IDENTITY) //主キーの値の生成戦略____//IDENTITYはID列を使用する場合に指定
	private Integer id;
	
	@Column(name="content", length=100, nullable=false)
	private String content;
	
	@Column(name="created_at", nullable=false)
	private Timestamp created_at;
	
	@Column(name="updated_at", nullable=false)
	private Timestamp updated_at;


	//^^^^ｹﾞｯﾀｰ・ｾｯﾀｰ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
}

