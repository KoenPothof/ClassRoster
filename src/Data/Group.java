package Data;

import NPC.NPC;

public class Group {

    private String group;
    private NPC[] NPCs = new NPC[16];


    public Group(String group) {
        this.group = group;
        for (int i = 0; i < NPCs.length; i++) {
            NPCs[i] = new NPC();
        }
    }

    @Override
    public String toString() {
        return group;
    }

    public NPC[] getNPCs() {
        return NPCs;
    }
}
