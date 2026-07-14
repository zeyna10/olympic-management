package com.cio.olympic.soap;

import com.cio.olympic.model.Athlete;
import com.cio.olympic.repository.AthleteRepository;
import com.cio.olympic.athlete.GetAthleteRequest;
import com.cio.olympic.athlete.GetAthleteResponse;
import com.cio.olympic.athlete.GetAllAthletesRequest;
import com.cio.olympic.athlete.GetAllAthletesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.ZoneId;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Endpoint
public class AthleteSoapEndpoint {

    private static final String NAMESPACE_URI = "http://cio.com/olympic/athlete";

    @Autowired
    private AthleteRepository athleteRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAthleteRequest")
    @ResponsePayload
    public GetAthleteResponse getAthlete(@RequestPayload GetAthleteRequest request) {
        GetAthleteResponse response = new GetAthleteResponse();
        Athlete athlete = athleteRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Athlete non trouve avec id: " + request.getId()));
        response.setAthlete(toXmlAthlete(athlete));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAthletesRequest")
    @ResponsePayload
    public GetAllAthletesResponse getAllAthletes(@RequestPayload GetAllAthletesRequest request) {
        GetAllAthletesResponse response = new GetAllAthletesResponse();
        for (Athlete athlete : athleteRepository.findAll()) {
            response.getAthlete().add(toXmlAthlete(athlete));
        }
        return response;
    }

    private com.cio.olympic.athlete.Athlete toXmlAthlete(Athlete athlete) {
        com.cio.olympic.athlete.Athlete xmlAthlete = new com.cio.olympic.athlete.Athlete();
        xmlAthlete.setId(athlete.getId());
        xmlAthlete.setNom(athlete.getNom());
        xmlAthlete.setPrenom(athlete.getPrenom());
        xmlAthlete.setSexe(athlete.getSexe());
        xmlAthlete.setDateNaissance(toXmlDate(athlete.getDateNaissance()));
        xmlAthlete.setNationalite(athlete.getPays() != null ? athlete.getPays().getNom() : null);
        xmlAthlete.setDiscipline(athlete.getDiscipline() != null ? athlete.getDiscipline().getNom() : null);
        if (athlete.getTaille() != null) xmlAthlete.setTaille(athlete.getTaille());
        if (athlete.getPoids() != null) xmlAthlete.setPoids(athlete.getPoids());
        return xmlAthlete;
    }

    private XMLGregorianCalendar toXmlDate(java.time.LocalDate localDate) {
        try {
            GregorianCalendar calendar = GregorianCalendar.from(
                    localDate.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}