import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.util.List;
/**
 * ClothesDao类负责与数据库中t_clothes表进行交互，提供对服装数据的增删查改功能
 */
public class ClothesDao {
    // QueryRunner对象用于执行SQL语句
    private QueryRunner queryRunner = new QueryRunner();
    /**
     * 增加服装
     *
     * @param clothes 要增加的服装对象，包含服装的详细信息
     */
    public void addClothes(Clothes clothes)  {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getConnection();
            // SQL语句：插入服装信息
            String sql = "INSERT INTO t_clothes (name, style, price) VALUES (?, ?, ?)";
            // 执行更新操作
            queryRunner.update(connection, sql, clothes.getName(), clothes.getStyle(), clothes.getPrice());
        } catch (Exception e) {
            // 异常处理：将低级异常转换为RuntimeException
            throw new RuntimeException(e);
        } finally {
            // 关闭连接
            DbUtils.closeQuietly(connection);
        }
    }
    /**
     * 修改服装
     *
     * @param clothes 要修改的服装对象，包含更新后的服装信息
     */
    public void updateClothes(Clothes clothes)  {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getConnection();
            // SQL语句：更新服装信息
            String sql = "UPDATE t_clothes SET name = ?, style = ?, price = ? WHERE id = ?";
            // 执行更新操作
            queryRunner.update(connection, sql, clothes.getName(), clothes.getStyle(), clothes.getPrice(), clothes.getId());
        } catch (Exception e) {
            // 异常处理：将低级异常转换为RuntimeException
            throw new RuntimeException(e);
        } finally {
            // 关闭连接
            DbUtils.closeQuietly(connection);
        }
    }
    /**
     * 查询所有服装
     *
     * @return 返回包含所有服装的列表
     */
    public List<Clothes> getAllClothes() {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getConnection();
            // SQL语句：查询所有服装信息
            String sql = "SELECT * FROM t_clothes";
            // 结果集处理：将查询结果转换为Clothes对象列表
            ResultSetHandler<List<Clothes>> handler = new BeanListHandler<>(Clothes.class);
            // 执行查询操作并返回结果
            return queryRunner.query(connection, sql, handler);
        } catch (Exception e) {
            // 异常处理：将低级异常转换为RuntimeException
            throw new RuntimeException(e);
        } finally {
            // 关闭连接
            DbUtils.closeQuietly(connection);
        }
    }
    /**
     * 删除服装
     *
     * @param id 要删除的服装的ID
     */
    public void deleteClothes(int id)  {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getConnection();
            // SQL语句：删除指定ID的服装信息
            String sql = "DELETE FROM t_clothes WHERE id = ?";
            // 执行更新操作
            queryRunner.update(connection, sql, id);
        } catch (Exception e) {
            // 异常处理：将低级异常转换为RuntimeException
            throw new RuntimeException(e);
        } finally {
            // 关闭连接
            DbUtils.closeQuietly(connection);
        }
    }
    /**
     * 根据服装名称模糊查找服装
     *
     * @param name 服装名称的关键字
     * @return 返回匹配的服装列表
     */
    public List<Clothes> selectByName(String name) {
        Connection connection = null;
        try {
            // 获取数据库连接
            connection = DruidUtil.getConnection();
            // SQL语句：根据名称模糊查询服装信息
            String sql = "SELECT * FROM t_clothes WHERE name LIKE ?";
            // 结果集处理：将查询结果转换为Clothes对象列表
            ResultSetHandler<List<Clothes>> handler = new BeanListHandler<>(Clothes.class);
            // 执行查询操作并返回结果
            return queryRunner.query(connection, sql,handler,"%" + name + "%");
        } catch (Exception e) {
            // 异常处理：将低级异常转换为RuntimeException
            throw new RuntimeException(e);
        } finally {
            // 关闭连接
            DbUtils.closeQuietly(connection);
        }
    }
}
