package kr.co.oraclejava.item.dto;

import org.modelmapper.ModelMapper;

import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.item.entity.ItemImg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemImgDto {

	private Long id;		
	private String imgName;		//이미지 파일명
	private String oriImgName;	//원본 이미지 파일명
	private String imgUrl;		//이미지 파일 경로
	private String repImgYn;	//대표 이미지 여부 결정

	private static ModelMapper modelMapper = new ModelMapper();
	
	public static ItemImgDto entityToDto(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class); 		
	}
	
	
	
}
