package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CateoryRequestDTO;
import com.Shop.Shop.DTO.CateoryResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import java.util.List;

public interface CateoryServiceTemp {
    CateoryResponseDTO findCateoryById(Long id) throws ExceptionHandlerNotFound;
    List<CateoryResponseDTO> findCateoryAll();
    CateoryResponseDTO  createCateory(CateoryRequestDTO cateoryRequestDTO);
    CateoryResponseDTO   updateCateory(Long id,CateoryRequestDTO cateoryRequestDTO);
    CateoryResponseDTO  deleteCateoryById(Long id) throws ExceptionHandlerNotFound;
}
