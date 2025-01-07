//package tequila.ticketbookingplatform.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//public class UserController {
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable String id) {
//        try {
//
//            UserResponse userById = userService.getUserById(id);
//            return ResponseEntity.ok(userById);
//        } catch (StaffNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//
//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> allUsers = userService.getAllUsers();
//        return ResponseEntity.ok(allUsers);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserDTO updatedUser) {
//        try {
//            userService.updateUser(id,updatedUser);
//            return ResponseEntity.ok("User updated successfully");
//        } catch (StaffNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable String id) {
//        try {
//            userService.deleteUser(id);
//            return ResponseEntity.ok("User deleted successfully");
//        } catch (StaffNotFoundException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user");
//        }
//    }
//}
