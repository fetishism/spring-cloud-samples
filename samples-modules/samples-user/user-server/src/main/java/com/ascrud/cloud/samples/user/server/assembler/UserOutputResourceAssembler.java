package com.ascrud.cloud.samples.user.server.assembler;

import com.ascrud.cloud.samples.user.common.entity.UserInput;
import com.ascrud.cloud.samples.user.common.entity.UserOutput;
import com.ascrud.cloud.samples.user.server.controller.UserController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author walkman
 */
@Component
public class UserOutputResourceAssembler implements ResourceAssembler<UserOutput, Resource<UserOutput>> {

    @Override
    public Resource<UserOutput> toResource(UserOutput userOutput) {
        return new Resource<>(
                userOutput,
                linkTo(methodOn(UserController.class).getUserById(userOutput.getUserId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUserList()).withRel("users"),
                linkTo(methodOn(UserController.class).deleteUserById(userOutput.getUserId())).withRel("delete_user"),
                linkTo(methodOn(UserController.class).getUserPermissionByUserId(userOutput.getUserId())).withRel("user_permissions"),
                linkTo(methodOn(UserController.class).getUserTokenByUsername(userOutput.getUsername(), "password")).withRel("user_token"),
                linkTo(methodOn(UserController.class).refreshToken(null)).withRel("user_refresh_token"),
                linkTo(methodOn(UserController.class).createUser(new UserInput(), null)).withRel("create_user")
        );
    }
}
