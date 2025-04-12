package sejevic.com._DWorldGenerationGame;

import org.junit.Test;
import sejevic.com._DWorldGenerationGame.core.utils.UnionFind;

import static com.google.common.truth.Truth.assertThat;

public class UnionFindTest {
    @Test
    public void testInitialParent() {
        UnionFind<String> uf;

        uf = new UnionFind<>();
        uf.addItem("A");
        uf.addItem("B");
        uf.addItem("C");
        uf.addItem("D");

        // Each element should be its own parent
        assertThat(uf.find("A")).isEqualTo("A");
        assertThat(uf.find("B")).isEqualTo("B");
        assertThat(uf.find("C")).isEqualTo("C");
        assertThat(uf.find("D")).isEqualTo("D");
        }

        @Test
        public void testUnionChangesParent() {
            UnionFind<String> uf;

            uf = new UnionFind<>();
            uf.addItem("A");
            uf.addItem("B");
            uf.addItem("C");
            uf.addItem("D");

             uf.union("A", "B");

            String parentA = uf.find("A");
            String parentB = uf.find("B");

            // After union, both should have the same root
            assertThat(parentA).isEqualTo(parentB);
        }

        @Test
        public void testTransitiveUnion() {
            UnionFind<String> uf;

            uf = new UnionFind<>();
            uf.addItem("A");
            uf.addItem("B");
            uf.addItem("C");
            uf.addItem("D");

            uf.union("A", "B");
            uf.union("B", "C");

            // All three should now have the same root
            String rootA = uf.find("A");
            String rootB = uf.find("B");
            String rootC = uf.find("C");

            assertThat(rootA).isEqualTo(rootB);
            assertThat(rootB).isEqualTo(rootC);
        }

        @Test
        public void testNoCycle() {
            UnionFind<String> uf;

            uf = new UnionFind<>();
            uf.addItem("A");
            uf.addItem("B");
            uf.addItem("C");
            uf.addItem("D");

            uf.union("A", "B");
            uf.union("C", "D");

            String rootAB = uf.find("A");
            String rootCD = uf.find("C");

            assertThat(rootAB).isNotEqualTo(rootCD);
        }
}
