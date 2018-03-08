package studio.blackmonkey.chat.server.repository;

import org.springframework.stereotype.Service;
import studio.blackmonkey.chat.server.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("UserRepository")
public class UserRepository {

    private Map<String, User> mSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, User user) {
        mSessions.put(sessionId, user);
    }

    public void remove(String sessionId) {
        mSessions.remove(sessionId);
    }

    public User getUser(String sessionId) {
        return mSessions.get(sessionId);
    }

    public Collection<User> getUsers() {
        return mSessions.values();
    }

    public boolean hasSession(String sessionId) {
        return mSessions.containsKey(sessionId);
    }
}
