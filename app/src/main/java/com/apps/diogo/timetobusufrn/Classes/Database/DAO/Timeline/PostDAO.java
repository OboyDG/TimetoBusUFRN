package com.apps.diogo.timetobusufrn.Classes.Database.DAO.Timeline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apps.diogo.timetobusufrn.Classes.Database.BancoTimeline;
import com.apps.diogo.timetobusufrn.Classes.Modelos.Post;

/**
 * Created by Diogo on 27/10/2017.
 */

public class PostDAO
{
    private SQLiteDatabase db;
    private BancoTimeline banco;
    
    public PostDAO(Context context)
    {
        banco = new BancoTimeline(context);
    }
    
    public long insertPost(Post post)
    {
        ContentValues valores;
        long resultado;
        
        db = banco.getWritableDatabase();
        
        valores = new ContentValues();
        
        //valores.put(BancoTimeline.ID, post.getId());
        valores.put(BancoTimeline.PARADA, post.getParada());
        valores.put(BancoTimeline.TIPOONIBUS, post.getTipoOnibus());
        valores.put(BancoTimeline.EMPRESAONIBUS, post.getEmpresaOnibus());
        valores.put(BancoTimeline.HORA, post.getHora());
        valores.put(BancoTimeline.SEGUNDOS, post.getSegundos());
        valores.put(BancoTimeline.COMENTARIO, post.getComentario());
        valores.put(BancoTimeline.MATRIUSUARIO, post.getUsuario().getMatricula());
        
        resultado = db.insert(BancoTimeline.TABELA2, null, valores);
        
        db.close();
        
        return resultado;
    }
    
    public Cursor selectAllPosts()
    {
        Cursor cursor;
        
        String[] campos =  {banco.ID, banco.PARADA, banco.TIPOONIBUS, banco.EMPRESAONIBUS, banco.HORA, banco.SEGUNDOS, banco.COMENTARIO, banco.MATRIUSUARIO};
        
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA2, campos, null, null, null, null, banco.HORA + " asc " , null);
        
        if(cursor!=null){
            cursor.moveToFirst();
        }
        
        db.close();
        
        return cursor;
    }
    
    public Cursor selectPostByID(int id)
    {
        Cursor cursor;
        String[] campos =  {banco.ID, banco.PARADA, banco.TIPOONIBUS, banco.EMPRESAONIBUS, banco.HORA, banco.SEGUNDOS, banco.COMENTARIO};
        
        String where = BancoTimeline.ID + "=" + id;
        
        db = banco.getReadableDatabase();
        
        cursor = db.query(BancoTimeline.TABELA2,campos,where, null, null, null, null, null);
        
        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    
    public void updatePost(Post post)
    {
        ContentValues valores;
        String where;
        
        db = banco.getWritableDatabase();
        
        where = BancoTimeline.ID + "=" + post.getId();
        
        valores = new ContentValues();
        valores.put(BancoTimeline.PARADA, post.getParada());
        valores.put(BancoTimeline.TIPOONIBUS, post.getTipoOnibus());
        valores.put(BancoTimeline.EMPRESAONIBUS, post.getEmpresaOnibus());
        valores.put(BancoTimeline.HORA, post.getHora());
        valores.put(BancoTimeline.SEGUNDOS, post.getSegundos());
        valores.put(BancoTimeline.COMENTARIO, post.getComentario());
        
        db.update(BancoTimeline.TABELA2,valores,where,null);
        db.close();
    }
    
    public void deletePost(int id)
    {
        String where = BancoTimeline.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(BancoTimeline.TABELA2,where,null);
        db.close();
    }
}