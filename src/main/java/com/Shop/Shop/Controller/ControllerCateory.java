package com.Shop.Shop.Controller;

import com.Shop.Shop.DTO.CateoryRequestDTO;
import com.Shop.Shop.DTO.CateoryResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Service.CateoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class ControllerCateory {

    private final CateoryService cateoryService;


    @PostMapping
    public ResponseEntity<CateoryResponseDTO> addCategory(@Valid @RequestBody CateoryRequestDTO cateoryRequestDTO) {
        CateoryResponseDTO response = cateoryService.createCateory(cateoryRequestDTO);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<CateoryResponseDTO>> getAllCategories() {
        List<CateoryResponseDTO> responseList = cateoryService.findCateoryAll();
        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CateoryResponseDTO> getCategoryById(@PathVariable Long id) throws ExceptionHandlerNotFound {
        CateoryResponseDTO response = cateoryService.findCateoryById(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) throws ExceptionHandlerNotFound {
        cateoryService.deleteCateoryById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CateoryResponseDTO> Update(@PathVariable Long id, @RequestBody CateoryRequestDTO cateoryRequestDTO)
            throws ExceptionHandlerNotFound {
        return ResponseEntity.ok(cateoryService.updateCateory(id, cateoryRequestDTO));
    }
}
