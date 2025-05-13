package poo.project.Services;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import poo.project.Dtos.StoreDTO;
import poo.project.Entities.Store;
import poo.project.Exceptiions.StoreNotFoundException;
import poo.project.Mappers.StoreMapperImp;
import poo.project.Repositories.StoreRepository;
import poo.project.Security.Entities.AppUser;
import poo.project.Security.Service.AccountService;
import poo.project.Utils.ApiResponse;

@Service
@AllArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final AccountService accountService;
    private final StoreMapperImp storeMapperImp;

    public ResponseEntity<ApiResponse<StoreDTO>> getStoreSettings(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser currentUser = accountService.loadUserByEmail(username);
        StoreDTO storeDTO = storeMapperImp.toDTO(currentUser.getStore());
        return ResponseEntity.ok(new ApiResponse<>(true,"Store information", storeDTO));
    }

    public ResponseEntity<ApiResponse<StoreDTO>> saveStoreInformation(StoreDTO storeDTO) throws StoreNotFoundException {
        Store store = storeRepository.findById(storeDTO.getId()).orElseThrow(()-> new StoreNotFoundException("Store not found"));
        storeMapperImp.updateEntity(store, storeDTO);
        Store savedStore = storeRepository.save(store);
        return ResponseEntity.ok(new ApiResponse<>(true,"data has been updated successfully",storeMapperImp.toDTO(savedStore)));
    }

//    public ResponseEntity<ApiResponse<StoreDTO>> saveStoreSchedule(StoreDTO storeDTO) throws StoreNotFoundException {
//
//    }

    }
