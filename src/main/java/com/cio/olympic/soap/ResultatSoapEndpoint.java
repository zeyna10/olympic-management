package com.cio.olympic.soap;

import com.cio.olympic.dto.MedailleDTO;
import com.cio.olympic.model.Resultat;
import com.cio.olympic.repository.ResultatRepository;
import com.cio.olympic.resultat.*;
import com.cio.olympic.service.MedailleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ResultatSoapEndpoint {

    private static final String NAMESPACE_URI = "http://cio.com/olympic/resultat";

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private MedailleService medailleService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getResultatRequest")
    @ResponsePayload
    public GetResultatResponse getResultat(@RequestPayload GetResultatRequest request) {
        GetResultatResponse response = new GetResultatResponse();
        Resultat resultat = resultatRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Resultat non trouve avec id: " + request.getId()));
        response.setResultat(toXmlResultat(resultat));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getResultatsByAthleteRequest")
    @ResponsePayload
    public GetResultatsByAthleteResponse getResultatsByAthlete(@RequestPayload GetResultatsByAthleteRequest request) {
        GetResultatsByAthleteResponse response = new GetResultatsByAthleteResponse();
        for (Resultat resultat : resultatRepository.findByAthleteId(request.getAthleteId())) {
            response.getResultat().add(toXmlResultat(resultat));
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTableauMedaillesRequest")
    @ResponsePayload
    public GetTableauMedaillesResponse getTableauMedailles(@RequestPayload GetTableauMedaillesRequest request) {
        GetTableauMedaillesResponse response = new GetTableauMedaillesResponse();
        List<MedailleDTO> tableau = medailleService.getTableauMedailles();
        for (MedailleDTO dto : tableau) {
            MedaillePays mp = new MedaillePays();
            mp.setPays(dto.getPays());
            mp.setCodeIso(dto.getCodeIso());
            mp.setOr(dto.getOr());
            mp.setArgent(dto.getArgent());
            mp.setBronze(dto.getBronze());
            mp.setTotal(dto.getTotal());
            response.getMedaillePays().add(mp);
        }
        return response;
    }

    private com.cio.olympic.resultat.Resultat toXmlResultat(Resultat resultat) {
        com.cio.olympic.resultat.Resultat xmlResultat = new com.cio.olympic.resultat.Resultat();
        xmlResultat.setId(resultat.getId());
        xmlResultat.setPosition(resultat.getPosition());
        xmlResultat.setPerformance(resultat.getPerformance());
        xmlResultat.setMedaille(resultat.getMedaille().name());
        xmlResultat.setEpreuveNom(resultat.getEpreuve() != null ? resultat.getEpreuve().getNom() : null);
        xmlResultat.setAthleteNom(resultat.getAthlete() != null ? resultat.getAthlete().getNom() : null);
        xmlResultat.setAthletePrenom(resultat.getAthlete() != null ? resultat.getAthlete().getPrenom() : null);
        return xmlResultat;
    }
}