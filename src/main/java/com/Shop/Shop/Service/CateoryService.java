package com.Shop.Shop.Service;

import com.Shop.Shop.DTO.CateoryRequestDTO;
import com.Shop.Shop.DTO.CateoryResponseDTO;
import com.Shop.Shop.Exception.ExceptionHandlerNotFound;
import com.Shop.Shop.Model.Entity.Category;
import com.Shop.Shop.Repository.CateoryRepository;
import com.Shop.Shop.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CateoryService implements CateoryServiceTemp {
    private final CateoryRepository cateoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Override
    public CateoryResponseDTO findCateoryById(Long id) throws ExceptionHandlerNotFound {
                var category =  cateoryRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("Not found"));
       return modelMapper.map(category, CateoryResponseDTO.class);
    }

    @Override
    public List<CateoryResponseDTO> findCateoryAll() {
        return cateoryRepository.findAll()
                .stream()
                .map(Category ->modelMapper.map(Category,CateoryResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CateoryResponseDTO createCateory(CateoryRequestDTO cateoryRequestDTO) {
        if(cateoryRepository.findByName(cateoryRequestDTO.getName()).isPresent()){
            throw new RuntimeException("name is already in use");
    }
        else{
            var category = modelMapper.map(cateoryRequestDTO, Category.class);
            var category1 =  cateoryRepository.save(category);
            return modelMapper.map(category1,CateoryResponseDTO.class) ;
        }
    }

    @Override
    public CateoryResponseDTO updateCateory(Long id, CateoryRequestDTO cateoryRequestDTO) throws ExceptionHandlerNotFound {
       var category =  cateoryRepository.findById(id)
               .orElseThrow(()-> new ExceptionHandlerNotFound("category not found"));
       log.info("password of category id {}",id);

       category.setName(cateoryRequestDTO.getName());
       return modelMapper.map(cateoryRepository.save(category), CateoryResponseDTO.class);
    }

    @Override
    public void deleteCateoryById(Long id) throws ExceptionHandlerNotFound {
                var category = cateoryRepository.findById(id)
                .orElseThrow(()->new ExceptionHandlerNotFound("Not found it"));
        cateoryRepository.deleteById(category.getId());
        modelMapper.map(category, CateoryResponseDTO.class);
    }

}
