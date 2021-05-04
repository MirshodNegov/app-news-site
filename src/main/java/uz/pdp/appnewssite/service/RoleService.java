package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDTO;
import uz.pdp.appnewssite.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName())) {
            return new ApiResponse("Bunday nomli role mavjud !",false);
        }
        Role role=new Role(
                roleDTO.getName(),
                roleDTO.getHuquqList(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editRole(Long id, RoleDTO roleDTO) {
        return new ApiResponse("",true);
    }
}
