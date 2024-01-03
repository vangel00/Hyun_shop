package kr.co.oraclejava.item.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import kr.co.oraclejava.item.dto.ItemFormDto;
import kr.co.oraclejava.item.dto.ItemSearchDto;
import kr.co.oraclejava.item.entity.Item;
import kr.co.oraclejava.item.service.ItemService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;
	
	@GetMapping("/admin/item/new")
	public String itemForm(Model model) {
		
		model.addAttribute("itemFormDto", new ItemFormDto());
		return "/item/itemForm";
	}
		
	@PostMapping("/admin/item/new")
	public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
							Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		
		//상품 등록시에 필수 값이 없으면 다시 상품 등록 페이지로 이동합니다.
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		/* 상품 등록 시 첫번째 이미지가 없다면 에러 메시지와 함께 상품 등록 페이지로 이동합니다.
		 * 상품의 첫번째 이미지는 메인 페이지에서 보여 줄 상품 이미지로 사용하기 위해 필수 갓으로 지정.
		 */
		 if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
	            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
	            return "item/itemForm";
	        }
		
		 try {
	            itemService.saveItem(itemFormDto, itemImgFileList);
	        } catch (Exception e){
	            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
	            return "item/itemForm";
	        }		 
		 
		//상품이 정상적으로 등록되면 메인 페이지로 이동.
		 return "redirect:/";		
	}
	
	 @GetMapping(value = "/admin/item/{itemId}")
	    public String itemDetail(@PathVariable("itemId") Long itemId, Model model){

	        try {
	        	// 조회한 상품 데이터를 모델에 담아서 뷰로 전달
	            ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
	            model.addAttribute("itemFormDto", itemFormDto);
	        } catch(EntityNotFoundException e){
	            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
	            // 값 없는 빈 객체라도 들고 가야 합니다. 
	            model.addAttribute("itemFormDto", new ItemFormDto());
	            return "item/itemForm";
	        }
	        return "item/itemForm";
	    }
	
	// memberController.java 참조하여 코딩
	 @PostMapping(value = "/admin/item/{itemId}")
	    public String itemUpdate(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
	                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model){
	        if(bindingResult.hasErrors()){
	            return "item/itemForm";
	        }

	        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
	            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
	            return "item/itemForm";
	        }

	        try {  //상품 수정 로직 호출
	            itemService.updateItem(itemFormDto, itemImgFileList);
	        } catch (Exception e){
	            model.addAttribute("errorMessage", "상품 수정 중 에러가 발생하였습니다.");
	            return "item/itemForm";
	        }
	        return "redirect:/";
	    } 
	 
	@GetMapping("/admin/items")
	public String itemList() {
		return "item/itemList";
	}
	 
}
