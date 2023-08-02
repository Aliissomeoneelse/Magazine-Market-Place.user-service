package com.company.userservice;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.mapper.UserMapper;
import com.company.userservice.module.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.repository.UserRepositoryImpl;
import com.company.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

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

	@Test
	void testCreatedMethodException() {
		Mockito.when(this.userMapper.toDto(Mockito.any()))
				.thenThrow(RuntimeException.class);

		ResponseDto<UserDto> response = this.userService.create(UserDto.builder()
				.id(1)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build());

		Assertions.assertFalse(response.isSuccess());
		Assertions.assertEquals(-3, response.getCode());

	}

	@Test
	void testGetMethodPositive(){
		Integer id = 1;
		UserDto userDto = UserDto.builder()
				.id(1)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build();

		Mockito.when(this.userRepository.findByIdAndDeletedAtIsNull(id))
				.thenReturn(Optional.of(User.builder()
						.id(id)
						.firstname("Alisher")
						.lastname("Khudoyberdiev")
						.middleName("Khusanovich")
						.build()));
		Mockito.when(this.userMapper.toDto(Mockito.any()))
				.thenReturn(userDto);

		ResponseDto<UserDto> response = this.userService.get(id);

		Assertions.assertTrue(response.isSuccess());
		Assertions.assertNotNull(response.getData());
		Assertions.assertEquals(0, response.getCode());
		Assertions.assertEquals("OK", response.getMessage());

		Mockito.verify(this.userRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).toDto(Mockito.any());
	}

	@Test
	void testGetMethodNegative() {
		Integer id = 1;

		Mockito.when(this.userRepository.findByIdAndDeletedAtIsNull(id))
				.thenReturn(Optional.empty());

		ResponseDto<UserDto> response = this.userService.get(id);

		Assertions.assertFalse(response.isSuccess());
		Assertions.assertNull(response.getData());
		Assertions.assertEquals(-1, response.getCode());
		Assertions.assertEquals("Not found!", response.getMessage());

		Mockito.verify(this.userRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.any());
	}

	@Test
	void testUpdateMethodPositive() {
		Integer id = 1;
		UserDto userDto = UserDto.builder()
				.id(1)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build();


		Mockito.when(this.userRepository.findByIdAndDeletedAtIsNull(Mockito.any()))
				.thenReturn(Optional.of(User.builder()
						.id(id)
						.firstname("Alisher")
						.lastname("Khudoyberdiev")
						.middleName("Khusanovich")
						.build()));

		Mockito.when(this.userMapper.toDto(Mockito.any()))
				.thenReturn(userDto);

		ResponseDto<UserDto> response = this.userService.update(userDto, id);

		Assertions.assertTrue(response.isSuccess());
		Assertions.assertNotNull(response.getData());
		Assertions.assertEquals(0, response.getCode());
		Assertions.assertEquals("User successful updated!", response.getMessage());

		Mockito.verify(this.userRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.any());
		Mockito.verify(this.userRepository, Mockito.times(1)).save(Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).updateUsersFromDto(Mockito.any(), Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).toDto(Mockito.any());

	}

	@Test
	void testUpdateMethodNegative() {
		Integer id = 1;

		Mockito.when(this.userRepository.findByIdAndDeletedAtIsNull(id))
				.thenReturn(Optional.empty());

		ResponseDto<UserDto> response = this.userService.update(UserDto.builder()
				.id(id)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build(),id);

		Assertions.assertFalse(response.isSuccess());
		Assertions.assertNull(response.getData());
		Assertions.assertEquals(-1, response.getCode());

		Mockito.verify(this.userRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.any());;
	}

	@Test
	void testDeleteMethodPositive() {
		Integer id = 1;
		UserDto userDto = UserDto.builder()
				.id(1)
				.firstname("Alisher")
				.lastname("Khudoyberdiev")
				.middleName("Khusanovich")
				.build();


		Mockito.when(this.userRepository.findByIdAndDeletedAtIsNull(Mockito.any()))
				.thenReturn(Optional.of(User.builder()
						.id(id)
						.firstname("Alisher")
						.lastname("Khudoyberdiev")
						.middleName("Khusanovich")
						.build()));

		Mockito.when(this.userMapper.toDto(Mockito.any()))
				.thenReturn(userDto);

		ResponseDto<UserDto> response = this.userService.delete(id);

		Assertions.assertTrue(response.isSuccess());
		Assertions.assertNotNull(response.getData());
		Assertions.assertEquals(0, response.getCode());
		Assertions.assertEquals("User successful deleted!", response.getMessage());

		Mockito.verify(this.userRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.any());
		Mockito.verify(this.userRepository, Mockito.times(1)).save(Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).updateUsersFromDto(Mockito.any(), Mockito.any());
		Mockito.verify(this.userMapper, Mockito.times(1)).toDto(Mockito.any());

	}



}