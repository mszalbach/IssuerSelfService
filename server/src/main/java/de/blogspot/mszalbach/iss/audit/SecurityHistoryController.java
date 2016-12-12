package de.blogspot.mszalbach.iss.audit;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.domain.SecurityHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by ms on 12.12.16.
 */
@BasePathAwareController
public class SecurityHistoryController {

    @Autowired
    private final SecurityHistoryService historyService;



    public SecurityHistoryController( SecurityHistoryService historyService ) {
        this.historyService = historyService;
    }



    @GetMapping( path = "/securities/{id}/history" )
    public ResponseEntity<List> getHistory( @PathVariable( "id" ) Security security ) {
        return ResponseEntity.ok( historyService.getHistory( security ) );
    }

}
