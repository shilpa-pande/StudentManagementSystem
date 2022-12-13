package com.studentmanagement;

import com.studentmanagement.Dto.AppConstants;
import com.studentmanagement.entity.Role;
import com.studentmanagement.repository.RoleRepo;
import com.studentmanagement.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StudentManagementApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Autowired
	private RoleRepo roleRepo;




	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role =new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1 =new Role();
			role1.setId(AppConstants.Student_USER);
			role1.setName("ROLE_STUDENT");

			Role role2 =new Role();
			role2.setId(AppConstants.Teacher_USER);
			role2.setName("ROLE_TEACHER");

			List<Role> roles = List.of(role, role1,role2);
			List<Role> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.print(r.getName());
			});
		}catch(Exception e){

			e.printStackTrace();

		}


	}
}

