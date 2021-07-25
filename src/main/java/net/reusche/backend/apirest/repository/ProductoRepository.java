package net.reusche.backend.apirest.repository;

import net.reusche.backend.apirest.entity.Producto;
import net.reusche.backend.apirest.entity.interfaces.ProductoNameAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAllByEmpresa_IdEmpresaOrderByIdProductoDesc(int idEmpresa);

    boolean existsByNombre(String nombre);

    Optional<Producto> findByNombre(String nombre);
    /*
    public static final String FIND_ONLY_NAME_AND_ID = "SELECT producto.id_producto, producto.nombre " +
            "FROM producto LEFT OUTER JOIN subtipo_producto ON producto.id_subtipo_producto = subtipo_producto.id_subtipo_producto "+
            "LEFT OUTER JOIN tipo_producto ON subtipo_producto.id_tipo_producto = tipo_producto.id_tipo_producto "+
            "LEFT OUTER JOIN marca_producto ON producto.id_marca_producto = marca_producto.idmarca_producto "+
            "WHERE producto.id_empresa = ?1 AND producto.activo = 1 ORDER BY producto.idproducto DESC;";
    */
    public List<ProductoNameAndId> findAllByEmpresa_IdEmpresaAndActivoIsTrueOrderByIdProductoDesc(int idEmpresa);
}
