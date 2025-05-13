package poo.project.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import poo.project.Dtos.StoreDTO;
import poo.project.Entities.Store;

@Service
public class StoreMapperImp {

    public StoreDTO toDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        BeanUtils.copyProperties(store, storeDTO);
        return storeDTO;
    }

    public Store toEntity(StoreDTO storeDTO) {
        Store store = new Store();
        BeanUtils.copyProperties(storeDTO, store);
        return store;
    }

    public void updateEntity(Store classToBeUpdated, StoreDTO updatingClass) {
        BeanUtils.copyProperties(updatingClass, classToBeUpdated);
    }

}
