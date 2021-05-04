package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.aop.HuquqniTekshirish;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.RoleDTO;
import uz.pdp.appnewssite.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAnyAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

//    @PreAuthorize("hasAnyAuthority('EDIT_ROLE')")
    @HuquqniTekshirish(huquq = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@Valid @RequestBody RoleDTO roleDTO,@PathVariable Long id) {
        ApiResponse apiResponse = roleService.editRole(id,roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
