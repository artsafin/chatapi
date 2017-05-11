package com.artsafin.chatapi.domain.visitor;

import com.artsafin.chatapi.domain.client.Client;
import com.artsafin.chatapi.domain.client.ClientFactory;
import com.artsafin.chatapi.domain.client.UnsupportedClientException;
import com.artsafin.chatapi.domain.event.impl.AgentStatusChanged;
import com.artsafin.chatapi.domain.event.impl.MessageFromAgent;
import com.artsafin.chatapi.domain.repository.VisitorRepository;

public class VisitorService {
    private final VisitorRepository visitorRepo;
    private final ClientFactory clientFactory;

    public VisitorService(VisitorRepository visitorRepo, ClientFactory clientFactory) {
        this.visitorRepo = visitorRepo;
        this.clientFactory = clientFactory;
    }

    public Visitor getOrCreateVisitor(VisitInfo info) {
        PersistentVisitor pv = visitorRepo.findOneBy(new VisitorByInfoSpec(info));
        if (pv != null) {
            return Visitor.fromPersistent(pv);
        } else {
            Visitor v = Visitor.newVisitor(info);
            visitorRepo.add(v);
            return v;
        }
    }

    public Client createVisitorClient(VisitInfo info) throws UnsupportedClientException {
        Client client = clientFactory.create(info.clientType);
        client.allowEvents(MessageFromAgent.class, AgentStatusChanged.class);

        return client;
    }
}
