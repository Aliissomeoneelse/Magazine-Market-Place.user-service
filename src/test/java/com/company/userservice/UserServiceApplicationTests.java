package com.company.userservice;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.mapper.UserMapper;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.repository.UserRepositoryImpl;
import com.company.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

//@SpringBootTest
class UserServiceApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	private UserService userService;
	private UserMapper userMapper;
	private UserRepository userRepository;
	private UserRepositoryImpl userRepositoryImpl;

	@BeforeEach
	void initMethod() {
		this.userRepository = Mockito.mock(UserRepository.class);
		this.userMapper = Mockito.mock(UserMapper.class);
		this.userRepositoryImpl = Mockito.mock(UserRepositoryImpl.class);
		this.userService = new UserService(userMapper, userRepository,userRepositoryImpl);
	}

	@Test
	void testCreatedMethodPositive() {
		UserDto userDto = UserDto.builder()
				.id(1)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build();

		Mockito.when(this.userMapper.toDto(Mockito.any()))
				.thenReturn(userDto);

		ResponseDto<UserDto> response = this.userService.create(userDto);

		Assertions.assertNotNull(response.getData());
		Assertions.assertTrue(response.isSuccess());
		Assertions.assertEquals("User successful created!", response.getMessage());
		Assertions.assertEquals(0, response.getCode());

		Mockito.verify(this.userMapper, Mockito.times(1)).toEntity(Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).toDto(Mockito.any());
		Mockito.verify(this.userRepository, Mockito.times(1)).save(Mockito.any());

	}

}
