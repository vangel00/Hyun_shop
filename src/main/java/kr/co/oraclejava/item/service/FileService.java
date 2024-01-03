package kr.co.oraclejava.item.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service // 파일 만들고 지우기
@Slf4j
public class FileService {

	 public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
	        UUID uuid = UUID.randomUUID(); //자동으로 만들어준다.
	        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));//확장자
	        String savedFileName = uuid.toString() + extension; // 저장할 이름
	        String fileUploadFullUrl = uploadPath + "/" + savedFileName;// c:/shop/item

	        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
	        fos.write(fileData); // 파일이 생성됩니다.
	        fos.close();
	        return savedFileName;
	    }
	 
	 public void deleteFile(String filePath) throws Exception{
	        File deleteFile = new File(filePath);
	        
	        if(deleteFile.exists()) {
	            deleteFile.delete();
	            log.info("파일을 삭제하였습니다.");
	        } else {
	            log.info("파일이 존재하지 않습니다.");
	        }
	    }
}
