package kr.co.oraclejava.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="item_img")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemImg {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_img_id")
	private Long id;	  // 상품 코드
	
	private String imgName;		//이미지 파일명
	private String oriImgName;	//원본 이미지 파일명
	private String imgUrl;		//이미지 파일 경로
	private String repImgYn;	//대표 이미지 여부 결정
	
	// item image는 여러개, item은 1개
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	//자동변경감지
	public void updateItemImg(String imgName, String oriImgName, String imgUrl) {
		this.imgName = imgName;//수정 할 이미지 파일
		this.oriImgName = oriImgName;//원본 이미지 파일명
		this.imgUrl = imgUrl;// 이미지 경로 
	}
	
	
}
