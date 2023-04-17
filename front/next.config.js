/** @type {import('next').NextConfig} */
const nextConfig = {
  experimental: {
    appDir: true,
  },
  images: {
    domains: [
      "images.unsplash.com",
      "img1.daumcdn.net",
      "camo.githubusercontent.com",
    ],
  },
};

module.exports = nextConfig;
