import { create } from "zustand"

const getMockWhiskeyResults = () => {
  return [
    {
      id: "w1",
      name: "Lagavulin 16",
      distillery: "Lagavulin",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 16,
      abv: 43,
      price: 95000,
      rating: 4.7,
      flavor_profile: ["스모키", "피트", "바다향", "달콤함", "바닐라"],
      description:
        "아일라 지역의 대표적인 위스키로, 강한 피트와 스모키한 향이 특징입니다. 바다 향과 달콤한 바닐라 노트가 조화롭게 어우러져 있습니다. 캠핑이나 저녁 시간에 즐기기 좋은 깊은 풍미를 가지고 있어요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Lagavulin+16",
      match_score: 0.95,
    },
    {
      id: "w2",
      name: "Highland Park 12",
      distillery: "Highland Park",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 12,
      abv: 40,
      price: 65000,
      rating: 4.5,
      flavor_profile: ["가벼운 스모크", "꿀", "오렌지", "스파이시"],
      description:
        "오크니 섬에서 생산되는 균형 잡힌 위스키로, 가벼운 스모크와 꿀, 오렌지 향이 조화롭게 어우러져 있습니다. 입문자도 부담 없이 즐길 수 있는 균형 잡힌 맛이 특징이에요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Highland+Park+12",
      match_score: 0.89,
    },
    {
      id: "w3",
      name: "Talisker 10",
      distillery: "Talisker",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 10,
      abv: 45.8,
      price: 70000,
      rating: 4.4,
      flavor_profile: ["해풍", "후추", "스모키", "과일"],
      description:
        "스카이 섬의 유일한 증류소에서 생산되는 위스키로, 해풍과 후추 향이 특징적입니다. 스모키한 향과 함께 과일 향이 느껴집니다. 바다를 연상시키는 독특한 맛이 매력적이에요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Talisker+10",
      match_score: 0.85,
    },
    {
      id: "w4",
      name: "Ardbeg 10",
      distillery: "Ardbeg",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 10,
      abv: 46,
      price: 85000,
      rating: 4.6,
      flavor_profile: ["강한 피트", "스모키", "시트러스", "초콜릿"],
      description:
        "아일라 지역의 강한 피트 향이 특징인 위스키로, 스모키한 향과 함께 시트러스와 초콜릿 향이 느껴집니다. 강렬한 맛을 좋아하는 애호가들에게 인기 있는 위스키에요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Ardbeg+10",
      match_score: 0.82,
    },
    {
      id: "w5",
      name: "Bowmore 12",
      distillery: "Bowmore",
      country: "스코틀랜드",
      type: "싱글 몰트",
      age: 12,
      abv: 40,
      price: 60000,
      rating: 4.2,
      flavor_profile: ["중간 피트", "레몬", "꿀", "바다향"],
      description:
        "아일라 지역의 균형 잡힌 위스키로, 중간 정도의 피트 향과 함께 레몬, 꿀, 바다 향이 느껴집니다. 부담 없이 즐길 수 있는 스모키한 위스키를 찾는다면 좋은 선택이에요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Bowmore+12",
      match_score: 0.78,
    },
    {
      id: "w6",
      name: "Nikka From The Barrel",
      distillery: "Nikka",
      country: "일본",
      type: "블렌디드",
      abv: 51.4,
      price: 55000,
      rating: 4.5,
      flavor_profile: ["스파이시", "과일", "꿀", "오크"],
      description:
        "일본의 대표적인 위스키로, 높은 도수에도 불구하고 부드러운 목넘김이 특징입니다. 스파이시한 향과 과일, 꿀, 오크 향이 복합적으로 어우러져 있어요. 가성비 최고의 위스키 중 하나로 꼽혀요.",
      image_url: "https://via.placeholder.com/300x400/333/fff?text=Nikka+From+The+Barrel",
      match_score: 0.76,
    },
  ]
}

export const useWhiskeyStore = create((set, get) => ({
  query: "",
  results: [],
  isLoading: false,

  setQuery: (query) => set({ query }),
  setResults: (results) => set({ results }),
  setIsLoading: (isLoading) => set({ isLoading }),

  searchWhiskeys: async (query) => {
    set({ isLoading: true, query })

    // 실제 구현에서는 여기에 API 호출 로직이 들어갑니다
    await new Promise((resolve) => setTimeout(resolve, 1000)) // 로딩 시뮬레이션

    const results = getMockWhiskeyResults()
    set({ results, isLoading: false })

    return results
  },
}))
