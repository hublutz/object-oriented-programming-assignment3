package BusinessLayer.Tiles.Units;

import BusinessLayer.Tiles.Units.EnemyTiles.Enemy;

import java.util.Collection;
import java.util.List;

public interface HeroicUnit {

    public void castAbility(Collection<Enemy>... args);
}
