package com.treintaytres.vdc_backend.mapper;

import com.treintaytres.vdc_backend.model.User;
import com.treintaytres.vdc_backend.model.UserWithString;
import com.treintaytres.vdc_backend.response.bandInfo.Instrument;
import com.treintaytres.vdc_backend.response.bandInfo.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "username", target = "name")
    @Mapping(source = "profileImageUrl", target = "url")
    @Mapping(source = "perteneceJunta", target = "perteneceJunta")
    @Mapping(target = "isAdmin", expression = "java(isAdminFromRol(user.getRol()))")
    @Mapping(target = "instruments",expression = "java(combineInstruments(user.getPrimaryInstrument(), user.getInstruments()))")
    Member toMember(User user);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "name")
    @Mapping(source = "user.profileImageUrl", target = "url")
    @Mapping(target = "isAdmin", expression = "java(isAdminFromRol(userWithString.getUser().getRol()))")
    @Mapping(target = "instruments", expression = "java(combineInstruments(userWithString.getUser().getPrimaryInstrument(), new java.util.HashSet<>()))")
    @Mapping(source = "attendance", target = "attendance")
    Member toMember(UserWithString userWithString);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "url", target = "url")
    Instrument toInstrument(com.treintaytres.vdc_backend.model.Instrument instrument);

    List<Instrument> toInstruments(List<com.treintaytres.vdc_backend.model.Instrument> instruments);

    List<Member> toMembers(List<User> users);

    default String isAdminFromRol(Integer rol) {
        return (rol != null && rol == 10) ? "true" : "false";
    }

    default List<Instrument> combineInstruments(com.treintaytres.vdc_backend.model.Instrument primary, Set<com.treintaytres.vdc_backend.model.Instrument> others) {
        List<Instrument> combined = new ArrayList<>();
        if (primary != null) combined.add(toInstrument(primary));
        if (others != null) combined.addAll(toInstruments(new ArrayList<>(others)));
        return combined;
    }
}

