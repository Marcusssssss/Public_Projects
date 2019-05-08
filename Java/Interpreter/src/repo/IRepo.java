package repo;

import model.ProgState;

import java.util.List;

public interface IRepo {
    void addProgr(ProgState progState);
    void logPrgStateExec(ProgState state);
    List<ProgState> getPrgList();
    void setPrgList(List<ProgState> list);
    
    ProgState getPrgStateByID(int id);
    List<String> getPrgStatesIDs();
}
