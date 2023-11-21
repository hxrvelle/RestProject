package controller.mapperTests;

import org.example.controller.dto.PhoneIncomingDto;
import org.example.controller.dto.PhoneOutgoingDto;
import org.example.controller.mapper.PhoneDtoMapper;
import org.example.model.Phone;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneDtoMapperTests {
    private final PhoneDtoMapper mapper = PhoneDtoMapper.INSTANCE;

    @Test
    void testMapToDto() {
        Phone phone = new Phone();
        phone.setId(1);

        PhoneOutgoingDto dto = mapper.mapToDto(phone);

        assertEquals(phone.getId(), dto.getId());
    }

    @Test
    void testMapToEntity() {
        PhoneIncomingDto incomingDto = new PhoneIncomingDto();
        incomingDto.setPhoneNumber("8888888");

        Phone phone = mapper.mapToEntity(incomingDto);

        assertEquals(incomingDto.getPhoneNumber(), phone.getPhoneNumber());
    }

    @Test
    void testMapToDtoList() {
        Phone phone1 = new Phone();
        phone1.setId(1);

        Phone phone2 = new Phone();
        phone2.setId(2);

        List<Phone> phoneList = Arrays.asList(phone1, phone2);

        List<PhoneOutgoingDto> dtoList = mapper.mapToDtoList(phoneList);

        assertEquals(phoneList.size(), dtoList.size());

        for (int i = 0; i < phoneList.size(); i++) {
            assertEquals(phoneList.get(i).getId(), dtoList.get(i).getId());
        }
    }
}
