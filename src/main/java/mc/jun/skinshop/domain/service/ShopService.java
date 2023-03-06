package mc.jun.skinshop.domain.service;

import mc.jun.skinshop.domain.dto.shop.CreateSellDto;
import mc.jun.skinshop.domain.entity.member.Member;
import mc.jun.skinshop.domain.entity.shop.Sell;

import java.util.List;

public interface ShopService {

     List<Sell> getAll ();
     Sell createSell(Member member, CreateSellDto createSellDto);    // 판매글 생성
     List<Sell> getAllSellByMember(Member member); // 특정 맴버의 모든 판매글 가져오기
     Sell getSellById(Long id); // 특정 ID 판매글 가져오기
     Sell modifySell(Sell old, Sell n); // 판매글 수정
     void removeSell(Member member, Sell sell); // 판매글 삭제
}
